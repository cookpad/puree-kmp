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
            url: "https://github.com/cookpad/puree-kmp/releases/download/v0.0.11/PureeKMP.xcframework.zip",
            checksum: "f2a5357881cac95f01a0c8c66dd0602d09dbf1feb865636f37dbd0c89699dd19"
        ),
    ]
)
