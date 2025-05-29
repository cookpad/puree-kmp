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
            url: "https://github.com/cookpad/puree-kmp/releases/download/v1.0.2/PureeKMP.xcframework.zip",
            checksum: "3cb207a6fe45399945bad4a1dfb9280fd52513a04750f34d3004229e3cd15741"
        ),
    ]
)
