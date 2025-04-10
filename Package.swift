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
            url: "https://github.com/cookpad/puree-kmp/releases/download/v0.0.10/PureeKMP.xcframework.zip",
            checksum: "f7766bbf2c46f5a59ac1107f9b1b6da2201c0d650b636e1d048993d108d8b782"
        ),
    ]
)
