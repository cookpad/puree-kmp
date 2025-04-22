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
            url: "https://github.com/cookpad/puree-kmp/releases/download/v1.0.1/PureeKMP.xcframework.zip",
            checksum: "5e22bf1aee49ef9c237270e17dbdcac149dc75790ddc3992e4044de0fd4008ea"
        ),
    ]
)
