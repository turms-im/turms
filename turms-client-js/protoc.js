const exec = require('child_process').execSync;
const fs = require('fs');
const os = require('os');
const glob = require('glob');

const IS_LINUX = os.type() === 'Linux';
const PB_REL = 'https://github.com/protocolbuffers/protobuf/releases';
const PB_VERSION = "3.15.7";

function runCmds(cmds) {
    for (const cmd of cmds) {
        console.info(cmd);
        exec(cmd, {stdio: 'inherit'});
    }
}

function install() {
    const dir = './protoc';
    const file = IS_LINUX ? `protoc-${PB_VERSION}-linux-x86_64.zip` : `protoc-${PB_VERSION}-win64.zip`;

    if (IS_LINUX) {
        if (fs.existsSync(`${dir}/bin/protoc`)) {
            return;
        }
    } else if (fs.existsSync(`${dir}/bin/protoc.exe`)) {
        return;
    }

    if (!fs.existsSync(dir)) {
        fs.mkdirSync(dir);
    }

    runCmds([
        `curl -L ${PB_REL}/download/v${PB_VERSION}/${file} -o ${dir}/protoc.zip`,
        `tar -xf ${dir}/protoc.zip -C ${dir}`
    ]);
}

function compile() {
    const protoc = IS_LINUX
        ? './protoc/bin/protoc'
        : 'start /b /w ./protoc/bin/protoc.exe';
    const plugin = IS_LINUX
        ? './node_modules/.bin/protoc-gen-ts_proto'
        : 'protoc-gen-ts_proto=%CD%/node_modules/.bin/protoc-gen-ts_proto.cmd';
    const outDir = './src/model/proto';
    fs.rmdirSync(outDir, {recursive: true});
    fs.mkdirSync(outDir);
    // Use glob instead of the param "proto_path" because protoc-3.15.x-win64 won't accept
    // "./src/proto/*.proto" if there is no a proto file in the dir "./src/proto"
    const files = glob.sync("./src/proto/**/*.proto");
    runCmds([
        protoc +
        ` --plugin=${plugin}` +
        ' --ts_proto_opt=' +
        'esModuleInterop=true,' +
        'forceLong=string,' +
        'outputJsonMethods=false,' +
        'outputPartialMethods=false,' +
        'useOptionals=true' +
        ' --ts_proto_out=./src/model/proto' +
        ' -I./src/proto' +
        ' ' + files.join(" ")
    ]);
}

if (process.argv[2] === 'compile') {
    console.info('Compiling proto');
    compile();
    console.info('proto files have been compiled to ts files');
} else {
    console.info('Installing protoc');
    install();
    console.info('protoc has been installed');
}