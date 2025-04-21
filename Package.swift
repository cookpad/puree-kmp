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
            url: "https://github.com/cookpad/puree-kmp/releases/download/v1.0.0/PureeKMP.xcframework.zip",
            checksum: "808a0950eb10fd63daa964d185074c4ca94a790ed94da6d38685ec6c49fe6dc6"
        ),
    ]
)
