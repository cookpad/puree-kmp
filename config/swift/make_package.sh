#!/bin/sh
mv ../../puree/build/XCFrameworks/release/puree.xcframework.zip ./puree.xcframework.zip

VERSION="$1"
CHECKSUM=$(swift package compute-checksum ./puree.xcframework.zip)

npx ejs Package.swift.ejs -i '{"version": "'$VERSION'", "checksum": "'$CHECKSUM'"}' -o ../../Package.swift
