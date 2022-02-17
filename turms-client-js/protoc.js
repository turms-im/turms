const exec = require('child_process').execSync;
const fs = require('fs');
const os = require('os');
const glob = require('glob');

const IS_LINUX = os.type() === 'Linux';
const IS_ALPINE = IS_LINUX && fs.existsSync('/etc/alpine-release');
const PB_REL = 'https://github.com/protocolbuffers/protobuf/releases';
const PB_VERSION = '3.17.3';
const DIR = './protoc';

function runCmds(cmds) {
    for (const cmd of cmds) {
        console.info(cmd);
        exec(cmd, {stdio: 'inherit'});
    }
}

function getProtocPathInLinux() {
    const path = `${DIR}/bin/protoc`;
    if (fs.existsSync(path)) {
        return path;
    }
    if (fs.existsSync('/usr/bin/protoc')) {
        return '/usr/bin/protoc';
    }
}

function install() {
    const file = IS_LINUX ? `protoc-${PB_VERSION}-linux-x86_64.zip` : `protoc-${PB_VERSION}-win64.zip`;

    if (IS_LINUX) {
        if (getProtocPathInLinux()) {
            return;
        }
    } else if (fs.existsSync(`${DIR}/bin/protoc.exe`)) {
        return;
    }

    if (!fs.existsSync(DIR)) {
        fs.mkdirSync(DIR);
    }

    let installProtoc;
    if (IS_ALPINE) {
        // protoc binaries in https://github.com/protocolbuffers/protobuf/releases
        // cannot run in alpine and will fail with "not found"
        return runCmds(['apk add protoc']);
    }
    if (IS_LINUX) {
        const installUnzip = IS_ALPINE ? 'apk add unzip' : 'apt install unzip';
        installProtoc = `${installUnzip} && unzip ${DIR}/protoc.zip -d ${DIR} && chmod 755 -R ${DIR}`;
    } else {
        installProtoc = `tar -xf ${DIR}/protoc.zip -C ${DIR}`;
    }
    runCmds([
        `curl --create-dirs -L ${PB_REL}/download/v${PB_VERSION}/${file} -o ${DIR}/protoc.zip`,
        installProtoc
    ]);
}

function compile() {
    const protoc = IS_LINUX
        ? getProtocPathInLinux()
        : 'start /b /w ./protoc/bin/protoc.exe';
    const plugin = IS_LINUX
        ? './node_modules/.bin/protoc-gen-ts_proto'
        : 'protoc-gen-ts_proto=%CD%/node_modules/.bin/protoc-gen-ts_proto.cmd';
    const outDir = './src/model/proto';
    if (fs.existsSync(outDir)) {
        fs.rmSync(outDir, {recursive: true});
    }
    fs.mkdirSync(outDir);
    // Use glob instead of the param "proto_path" because protoc-3.15.x-win64 won't accept
    // "./src/proto/*.proto" if there is no a proto file in the dir "./src/proto"
    const files = glob.sync('./src/proto/**/*.proto');
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
        ' ' + files.join(' ')
    ]);
}

console.info(`Working dir: ${__dirname}`);

if (process.argv[2] === 'compile') {
    console.info('Compiling proto');
    compile();
    console.info('proto files have been compiled to ts files');
} else {
    console.info('Installing protoc');
    install();
    console.info('protoc has been installed');
}