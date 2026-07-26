// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "GoPeakDesignSystem",
    platforms: [
        .iOS(.v17)
    ],
    products: [
        .library(
            name: "GoPeakDesignSystem",
            targets: ["GoPeakDesignSystem"]
        )
    ],
    targets: [
        .target(
            name: "GoPeakDesignSystem",
            resources: [.process("Resources/Media.xcassets")]
        )
    ]
)
