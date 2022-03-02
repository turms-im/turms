const {readFileSync} = require('fs');
const {
    createSourceFile,
    isArrayBindingPattern,
    isConstructorDeclaration,
    isGetAccessorDeclaration,
    isIdentifier,
    isMethodDeclaration,
    isMethodSignature,
    isObjectBindingPattern,
    isPropertyDeclaration,
    isPropertySignature,
    isSetAccessorDeclaration,
    isTypeLiteralNode,
    isTypeReferenceNode,
    ScriptKind,
    ScriptTarget,
    SyntaxKind
} = require('typescript');

const Visibility = {
    Private: 0,
    Protected: 1,
    Public: 2
};

function getNodeName(node) {
    return node.name?.getText();
}

function getNodeVisibility(node) {
    if (!node.modifiers) {
        return;
    }
    for (const modifier of node.modifiers) {
        switch (modifier.kind) {
            case SyntaxKind.PublicKeyword:
                return Visibility.Public;
            case SyntaxKind.ProtectedKeyword:
                return Visibility.Protected;
            case SyntaxKind.PrivateKeyword:
                return Visibility.Private;
            default:
                break;
        }
    }
}

function getNodeType(node) {
    return node?.type?.getText();
}

function containsModifier(node, modifierKind) {
    return node.modifiers?.some(modifier => modifier.kind === modifierKind);
}

function isAsync(node) {
    return containsModifier(node, SyntaxKind.AsyncKeyword);
}

function isOptional(node) {
    return !!node.questionToken;
}

function isStatic(node) {
    return containsModifier(node, SyntaxKind.StaticKeyword);
}

function isConst(node) {
    return node?.kind === SyntaxKind.ConstKeyword;
}

function parseEnum(node) {
    return {
        syntax: 'enum',
        name: node.name.text,
        members: node.members.map(o => o.name.getText())
    };
}

function parseTypeAlias(node) {
    return {
        syntax: 'type_alias',
        name: node.name.text,
        members: (node.type.members || []).map(member => ({
            name: member.name.getText(),
            type: member.type.getText()
        }))
    };
}

function parseVariables(node) {
    const declarations = node.declarationList?.declarations || [];
    return declarations.map(declaration => {
        return {
            syntax: 'variable',
            name: declaration.name.getText(),
            isConst: node.declarationList.getChildren().some(o => isConst(o)),
            type: isTypeLiteralNode(declaration.type) ? parseInterface(declaration.type) : declaration.type.getText()
        };
    });
}

function parseInterface(node) {
    const interfaceDeclaration = {
        syntax: 'interface',
        name: getNodeName(node),
        typeParameters: (node.typeParameters || []).map(param => param.getText()),
        properties: [],
        methods: []
    };

    if (node.members) {
        node.members.forEach(member => {
            if (isPropertySignature(member)) {
                interfaceDeclaration.properties.push({
                    name: getNodeName(node),
                    visibility: Visibility.Public,
                    type: getNodeType(node),
                    isOptional: isOptional(member),
                    isStatic: isStatic(member)
                });
            } else if (isMethodSignature(member)) {
                interfaceDeclaration.methods.push({
                    name: getNodeName(node),
                    isAbstract: true,
                    visibility: Visibility.Public,
                    type: getNodeType(node),
                    isOptional: isOptional(member),
                    isStatic: isStatic(member),
                    isAsync: isAsync(member),
                    parameters: parseMethodParams(member)
                });
            }
        });
    }
    return interfaceDeclaration;
}

function parseMethodParams(node) {
    return node.parameters.reduce(
        (params, param) => {
            if (isIdentifier(param.name)) {
                params.push({
                    name: getNodeName(param),
                    type: getNodeType(param)
                });
            } else if (isObjectBindingPattern(param.name) || isArrayBindingPattern(param.name)) {
                const elements = param.name.elements;
                let types = [];
                const boundParam = {};
                if (param.type && isTypeReferenceNode(param.type)) {
                    boundParam.typeReference = getNodeType(param);
                } else if (param.type && isTypeLiteralNode(param.type)) {
                    types = param.type.elementTypes.map(type => type.getText())
                        || param.type.members.filter(member => isPropertySignature(member)).map(signature => signature.type.getText());
                }
                boundParam.parameters = elements.map((bindingElement, index) => ({
                    name: bindingElement.name.getText(),
                    type: types[index]
                }));
                params.push(boundParam);
            }
            return params;
        },
        []
    );
}

function parseClass(node) {
    const classDeclaration = {
        name: node.name.text,
        typeParameters: (node.typeParameters || []).map(param => param.getText()),
        properties: [],
        accessors: [],
        methods: []
    };

    if (node.members) {
        node.members.forEach((member) => {
            if (isPropertyDeclaration(member)) {
                classDeclaration.properties.push({
                    name: getNodeName(member),
                    visibility: getNodeVisibility(member),
                    type: getNodeType(member),
                    isOptional: isOptional(member),
                    isStatic: isStatic(member)
                });
            } else if (isGetAccessorDeclaration(member)) {
                classDeclaration.accessors.push({
                    syntax: 'get_accessor',
                    name: getNodeName(member),
                    visibility: getNodeVisibility(member),
                    type: getNodeType(member),
                    isStatic: isStatic(member)
                });
            } else if (isSetAccessorDeclaration(member)) {
                classDeclaration.accessors.push({
                    syntax: 'set_accessor',
                    name: getNodeName(member),
                    visibility: getNodeVisibility(member),
                    type: getNodeType(member),
                    isStatic: isStatic(member)
                });
            } else if (isConstructorDeclaration(member)) {
                classDeclaration.constructor = {
                    name: classDeclaration.name,
                    parameters: (member.parameters || []).flatMap(parameter => {
                        if (isIdentifier(parameter.name)) {
                            return {
                                name: getNodeName(parameter),
                                type: getNodeType(parameter)
                            };
                        } else if (isObjectBindingPattern(parameter.name) || isArrayBindingPattern(parameter.name)) {
                            return [...parameter.name.elements]
                                .map(element => {
                                    if (isIdentifier(element.name)) {
                                        return {
                                            name: getNodeName(element)
                                        };
                                    }
                                }).filter(Boolean);
                        }
                        return [];
                    })
                };
            } else if (isMethodDeclaration(member)) {
                classDeclaration.methods.push({
                    name: getNodeName(member),
                    visibility: getNodeVisibility(member),
                    type: getNodeType(member),
                    isOptional: isOptional(member),
                    isStatic: isStatic(member),
                    parameters: parseMethodParams(member)
                });
            }
        });
    }
    return classDeclaration;
}

module.exports = {
    parse(filePath) {
        const source = createSourceFile(
            filePath,
            readFileSync(filePath).toString(),
            ScriptTarget.Latest,
            true,
            ScriptKind.TS
        );
        const resource = source.getChildAt(0);
        const nodes = resource.getChildren();
        const declarations = [];
        for (let node = nodes.shift(); node; node = nodes.shift()) {
            switch (node.kind) {
                case SyntaxKind.EnumDeclaration:
                    declarations.push(parseEnum(node));
                    break;
                case SyntaxKind.TypeAliasDeclaration:
                    declarations.push(parseTypeAlias(node));
                    break;
                case SyntaxKind.VariableStatement:
                    declarations.push(...parseVariables(node));
                    break;
                case SyntaxKind.InterfaceDeclaration:
                    declarations.push(parseInterface(node));
                    break;
                case SyntaxKind.ClassDeclaration:
                    declarations.push(parseClass(node));
                    break;
                default:
                    break;
            }
            if ([SyntaxKind.ClassDeclaration,
                SyntaxKind.ModuleDeclaration,
                SyntaxKind.FunctionDeclaration].includes(node.kind)) {
                continue;
            }
            nodes.unshift(...node.getChildren());
        }
        return declarations;
    }

};
