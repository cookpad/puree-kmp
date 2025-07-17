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
            url: "https://github.com/cookpad/puree-kmp/releases/download/v1.0.4/PureeKMP.xcframework.zip",
            checksum: "c6950b9761cc22ae14aab15124bc75c8a73868d62bc2a1422b57bf123399dc73"
        ),
    ]
)
