// swift-tools-version:5.5
import PackageDescription

let package = Package(
    name: "PureeKMP",
    products: [
        .library(
            name: "PureeKMP",
            targets: ["PureeKMP"]
        ),
    ],
    dependencies: [],
    targets: [
        .binaryTarget(
            name: "PureeKMP",
            url: "https://github.com/cookpad/puree-kmp/releases/download/v0.0.12/PureeKMP.xcframework.zip",
            checksum: "6f3036a4daba698d535187d240a6c7993d6d668e9c8917843cef529f302972b8"
        ),
    ]
)
