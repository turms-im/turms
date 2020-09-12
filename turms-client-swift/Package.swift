// swift-tools-version:5.1

import PackageDescription

let package = Package(
    name: "TurmsClient",
    products: [
        .library(
            name: "TurmsClient",
            targets: ["TurmsClient"]),
    ],
    dependencies: [
        // Don't use Combine because of "@available(OSX 10.15, iOS 13.0, tvOS 13.0, watchOS 6.0, *)"
        .package(url: "https://github.com/mxcl/PromiseKit", from: "6.13.3"),
        .package(url: "https://github.com/apple/swift-protobuf", from: "1.12.0"),
        .package(url: "https://github.com/turms-im/Starscream", .branch("master")),
        
        // Dev deps
        .package(url: "https://github.com/shibapm/PackageConfig", from: "0.13.0"),
        .package(url: "https://github.com/nicklockwood/SwiftFormat", from: "0.46.2"),
        .package(url: "https://github.com/Realm/SwiftLint", from: "0.40.1"),
        .package(url: "https://github.com/orta/Komondor", from: "1.0.5")
    ],
    targets: [
        .target(
            name: "TurmsClient",
            dependencies: ["PromiseKit", "Starscream", "SwiftProtobuf"]),
        .testTarget(
            name: "TurmsClientTests",
            dependencies: ["TurmsClient", "Starscream", "SwiftProtobuf"]),
    ]
)

#if canImport(PackageConfig)
    import PackageConfig

    let config = PackageConfig([
        "komondor": [
            "pre-commit": [
                "swift run swiftformat .",
                "swift run swiftlint autocorrect --path Sources/",
                "git add ."
            ]
        ]
    ])
#endif
