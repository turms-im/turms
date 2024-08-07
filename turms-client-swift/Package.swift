// swift-tools-version:5.1

import PackageDescription

let package = Package(
    name: "TurmsClient",
    platforms: [
        .macOS(.v10_15),
        .iOS(.v13),
    ],
    products: [
        .library(
            name: "TurmsClient",
            targets: ["TurmsClient"]
        ),
    ],
    dependencies: [
        .package(url: "https://github.com/apple/swift-protobuf", from: "1.27.1"),

        // Dev deps
        // Only uncomment the dependencies when developing so that users don't need to resolve them
//        .package(url: "https://github.com/shibapm/PackageConfig", from: "1.1.3"),
//        .package(url: "https://github.com/nicklockwood/SwiftFormat", from: "0.54.3"),
//        .package(url: "https://github.com/SimplyDanny/SwiftLintPlugins", from: "0.55.1"),
//        .package(url: "https://github.com/orta/Komondor", from: "1.1.4"),
    ],
    targets: [
        .target(
            name: "TurmsClient",
            dependencies: ["SwiftProtobuf"]
        ),
        .testTarget(
            name: "TurmsClientTests",
            dependencies: ["TurmsClient", "SwiftProtobuf"]
        ),
    ],
    swiftLanguageVersions: [.v5]
)

#if canImport(PackageConfig)
    import PackageConfig

    let config = PackageConfig([
        "komondor": [
            "pre-commit": [
                "swift run swiftformat . --swiftversion 5 --selfrequired assertCompleted,wait",
                "swift run swiftlint --fix --path Sources/",
                "git add .",
            ],
        ],
    ])
#endif