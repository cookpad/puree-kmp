#!/bin/sh
mv ../../puree/build/XCFrameworks/release/puree.xcframework.zip ./PureeKMP.xcframework.zip

VERSION="$1"
CHECKSUM=$(swift package compute-checksum ./PureeKMP.xcframework.zip)

npx ejs Package.swift.ejs -i '{"version": "'$VERSION'", "checksum": "'$CHECKSUM'"}' -o ../../Package.swift
