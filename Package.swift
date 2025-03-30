// swift-tools-version:5.5
import PackageDescription

let package = Package(
    name: "puree-kmp",
    products: [
        .library(
            name: "puree-kmp",
            targets: ["puree-kmp"]
        ),
    ],
    dependencies: [],
    targets: [
        .binaryTarget(
            name: "puree-kmp",
            url: "https://github.com/cookpad/puree-kmp/releases/download/0.0.9/puree.xcframework.zip",
            checksum: "a891024688d98f5ecadea7045a85eb169e8ea2a8c7a1d4b465e992750e2c561b"
        ),
    ]
)
