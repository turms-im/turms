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
    Private: 'private',
    Protected: 'protected',
    Public: 'public'
};

function getNodeName(node) {
    return node.name?.getText();
}

const modifiers = {
    [SyntaxKind.PublicKeyword]: Visibility.Public,
    [SyntaxKind.ProtectedKeyword]: Visibility.Protected,
    [SyntaxKind.PrivateKeyword]: Visibility.Private
};

function getNodeVisibility(node) {
    return node.modifiers?.find(modifier => modifiers[modifier.kind]);
}

function getNodeType(node) {
    return node.type?.getText();
}

function containsModifier(node, modifierKind) {
    return node.modifiers?.some(modifier => modifier.kind === modifierKind);
}

function isAsync(node) {
    return containsModifier(node, SyntaxKind.AsyncKeyword);
}

function isConst(node) {
    return node.kind === SyntaxKind.ConstKeyword;
}

function isOptional(node) {
    return !!node.questionToken;
}

function isStatic(node) {
    return containsModifier(node, SyntaxKind.StaticKeyword);
}

function isNotStatic(node) {
    return !isStatic(node);
}

function isVisible(node) {
    const visibility = getNodeVisibility(node);
    return !visibility || visibility === 'public';
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
    const declaration = {
        syntax: 'interface',
        name: getNodeName(node),
        properties: [],
        methods: []
    };

    if (node.members) {
        node.members.forEach(member => {
            if (isPropertySignature(member)) {
                if (isNotStatic(member)) {
                    declaration.properties.push({
                        name: getNodeName(member),
                        type: getNodeType(member),
                        isOptional: isOptional(member)
                    });
                }
            } else if (isMethodSignature(member)) {
                if (isNotStatic(member)) {
                    declaration.methods.push({
                        name: getNodeName(member),
                        isAbstract: true,
                        type: getNodeType(member),
                        isOptional: isOptional(member),
                        isAsync: isAsync(member),
                        parameters: parseMethodParams(member)
                    });
                }
            }
        });
    }
    return declaration;
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
                // TODO: support TypeReference = 178
                let types;
                let isMember = false;
                if (param.type && isTypeLiteralNode(param.type)) {
                    types = param.type.elementTypes?.map(type => type.getText());
                    if (!types) {
                        types = param.type.members
                            .filter(member => isPropertySignature(member))
                            .map(signature => signature.type.getText());
                        isMember = true;
                    }
                } else {
                    types = [];
                }
                const elements = param.name.elements.map((bindingElement, index) => ({
                    name: bindingElement.name.getText(),
                    type: types[index]
                }));
                params.push({
                    typeReference: param.type && isTypeReferenceNode(param.type) ? getNodeType(param) : undefined,
                    parameters: isMember ? undefined : elements,
                    members: isMember ? elements : undefined,
                });
            }
            return params;
        },
        []
    );
}

function parseClass(node) {
    const declaration = {
        syntax: 'class',
        name: node.name.text,
        properties: [],
        accessors: [],
        methods: []
    };

    if (node.members) {
        node.members.forEach((member) => {
            if (isPropertyDeclaration(member)) {
                if (isVisible(member) && isNotStatic(member)) {
                    declaration.properties.push({
                        syntax: 'property',
                        name: getNodeName(member),
                        type: getNodeType(member),
                        isOptional: isOptional(member)
                    });
                }
            } else if (isGetAccessorDeclaration(member)) {
                if (isVisible(member) && isNotStatic(member)) {
                    declaration.accessors.push({
                        syntax: 'get_accessor',
                        name: getNodeName(member),
                        type: getNodeType(member)
                    });
                }
            } else if (isSetAccessorDeclaration(member)) {
                if (isVisible(member) && isNotStatic(member)) {
                    declaration.accessors.push({
                        syntax: 'set_accessor',
                        name: getNodeName(member),
                        type: getNodeType(member)
                    });
                }
            } else if (isConstructorDeclaration(member)) {
                declaration.constructor = {
                    name: declaration.name,
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
                if (isVisible(member) && isNotStatic(member)) {
                    declaration.methods.push({
                        name: getNodeName(member),
                        syntax: 'method',
                        type: getNodeType(member),
                        isOptional: isOptional(member),
                        parameters: parseMethodParams(member)
                    });
                }
            }
        });
    }
    return declaration;
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
        const nodes = source.getChildAt(0).getChildren();
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
            }
            if (![SyntaxKind.ClassDeclaration,
                SyntaxKind.ModuleDeclaration,
                SyntaxKind.FunctionDeclaration].includes(node.kind)) {
                nodes.unshift(...node.getChildren());
            }
        }
        return declarations;
    }

};
