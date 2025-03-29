#!/bin/sh
VERSION="$1"
CHECKSUM=$(swift package compute-checksum ../../puree/build/XCFrameworks/release/puree.xcframework.zip)

npx ejs Package.swift.ejs -i '{"version": "'$VERSION'", "checksum": "'$CHECKSUM'"}' -o ../../Package.swift
