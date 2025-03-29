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
            url: "https://github.com/cookpad/puree-kmp/releases/download/0.0.7/puree.xcframework.zip",
            checksum: "c5cacc01058bfe8bcc05450a8e626a0082b40f1913c7b0f0efe97cd8b4b8be64"
        ),
    ]
)
