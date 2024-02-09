// swift-tools-version:5.1

import PackageDescription

let package = Package(
    name: "TurmsClient",
    platforms: [
        .macOS(.v10_14),
        .iOS(.v12),
    ],
    products: [
        .library(
            name: "TurmsClient",
            targets: ["TurmsClient"]
        ),
    ],
    dependencies: [
        // Don't use Combine because of "@available(OSX 10.15, iOS 13.0, tvOS 13.0, watchOS 6.0, *)"
        .package(url: "https://github.com/mxcl/PromiseKit", from: "8.1.1"),
        .package(url: "https://github.com/apple/swift-protobuf", from: "1.25.2"),

        // Dev deps
        // Only uncomment the dependencies when developing so that users don't need to resolve them
//        .package(url: "https://github.com/shibapm/PackageConfig", from: "1.1.3"),
//        .package(url: "https://github.com/nicklockwood/SwiftFormat", from: "0.53.1"),
//        .package(url: "https://github.com/Realm/SwiftLint", from: "0.54.0"),
//        .package(url: "https://github.com/orta/Komondor", from: "1.1.4"),
    ],
    targets: [
        .target(
            name: "TurmsClient",
            dependencies: ["PromiseKit", "SwiftProtobuf"]
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
                "swift run swiftformat .",
                "swift run swiftlint autocorrect --path Sources/",
                "git add .",
            ],
        ],
    ])
#endif
