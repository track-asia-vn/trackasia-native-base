#!/usr/bin/env bash

MASON_NAME=libosmium
MASON_VERSION=2.15.1
MASON_HEADER_ONLY=true

. ${MASON_DIR}/mason.sh

function mason_load_source {
    mason_download \
        https://github.com/osmcode/${MASON_NAME}/archive/v${MASON_VERSION}.tar.gz \
        b73b2837906806f67c65045921070d3c6b0a9859

    mason_extract_tar_gz

    export MASON_BUILD_PATH=${MASON_ROOT}/.build/${MASON_NAME}-${MASON_VERSION}
}

function mason_compile {
    mkdir -p ${MASON_PREFIX}/include/
    cp -r include/osmium ${MASON_PREFIX}/include/osmium
}

function mason_cflags {
    echo "-I${MASON_PREFIX}/include"
}

function mason_ldflags {
    :
}

mason_run "$@"
