module.exports = {
    "roots": [
        "<rootDir>/src/",
        "<rootDir>/tests/"
    ],
    verbose: true,
    "transform": {
        "^.+\\.tsx?$": "ts-jest"
    },
    globals: {
        "ts-jest": {
            diagnostics: false
        }
    },
    setupFilesAfterEnv: ['./tests/setup.ts']
};