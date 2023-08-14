#!/usr/bin/env bash

MASON_NAME=binutils
MASON_VERSION=2.31
MASON_LIB_FILE=lib/libbfd.a

. ${MASON_DIR}/mason.sh

function mason_load_source {
    mason_download \
        http://ftp.gnu.org/gnu/binutils/${MASON_NAME}-${MASON_VERSION}.tar.bz2 \
        602856fd5af10a09a123ae25dfb86e3b436549cf

    mason_extract_tar_bz2

    export MASON_BUILD_PATH=${MASON_ROOT}/.build/${MASON_NAME}-${MASON_VERSION}
}

function mason_compile {
    # Add optimization flags since CFLAGS overrides the default (-g -O2)
    export CFLAGS="${CFLAGS} -O3 -DNDEBUG -Wno-c++11-narrowing"
    export CXXFLAGS="${CXXFLAGS} -O3 -DNDEBUG -Wno-c++11-narrowing"
    ./configure \
        --prefix=${MASON_PREFIX} \
        --enable-gold \
        --enable-plugins \
        --enable-static \
        --disable-shared \
        --disable-dependency-tracking

    make -j${MASON_CONCURRENCY}
    make install
    cp include/libiberty.h ${MASON_PREFIX}/include/
    cp libiberty/libiberty.a ${MASON_PREFIX}/lib/
}

function mason_cflags {
    echo -I${MASON_PREFIX}/include
}

function mason_ldflags {
    :
}

function mason_static_libs {
    echo ${MASON_PREFIX}/${MASON_LIB_FILE}
}

function mason_clean {
    make clean
}

mason_run "$@"
