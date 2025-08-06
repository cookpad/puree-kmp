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
            url: "https://github.com/cookpad/puree-kmp/releases/download/v1.0.5/PureeKMP.xcframework.zip",
            checksum: "8e21feeb1f9294eda182c3d49ed1be0b12e01a7f770e5044ccc444924f6bf0b2"
        ),
    ]
)
