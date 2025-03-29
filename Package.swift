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
            url: "https://github.com/cookpad/puree-kmp/releases/download/0.0.8/puree.xcframework.zip",
            checksum: "411f239368158f931a37729bc07c0d2e4d8eef64caa79628d3d7bab7f4848603"
        ),
    ]
)
