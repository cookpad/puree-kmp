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
            url: "https://github.com/cookpad/puree-kmp/releases/download/0.0.9/PureeKMP.xcframework.zip",
            checksum: "770ed3458328bd409e52dc8ab28e0d0927bf21ee98b0342da62dd57164b9849a"
        ),
    ]
)
