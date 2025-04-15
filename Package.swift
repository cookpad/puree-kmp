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
            url: "https://github.com/cookpad/puree-kmp/releases/download/v0.0.13/PureeKMP.xcframework.zip",
            checksum: "62012e6c213a9a919a7279a0a215996655820f91fd882bbe211a91ae8a6bab85"
        ),
    ]
)
