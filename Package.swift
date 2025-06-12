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
            url: "https://github.com/cookpad/puree-kmp/releases/download/v1.0.3/PureeKMP.xcframework.zip",
            checksum: "2b5ce7cda354a64df9679e25e6e0bcf84883aad84f128b741ab1fdd0805245a2"
        ),
    ]
)
