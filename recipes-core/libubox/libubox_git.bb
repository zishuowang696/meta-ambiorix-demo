SUMMARY = "C utility functions for OpenWrt"
HOMEPAGE = "https://git.openwrt.org/project/libubox.git"
LICENSE = "BSD-1-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = "\
    file://avl.c;endline=39;md5=00810155fed3d604816ec5814523d60a \
    file://avl-cmp.c;endline=15;md5=1603e6094b432a5f3f320877a06f41b5 \
    file://base64.c;endline=61;md5=51fdff010d45b0086ac0a6e035693dc0 \
    file://blobmsg.c;endline=15;md5=7ed64c1570e8c9b46c4fc6fbd16c489e \
    file://list.h;endline=28;md5=2d5f5475fbd0f08741354c5a99c2e983 \
    file://md5.h;endline=39;md5=048bf9f68963c207a0c2b3a94c9d2aaa \
    file://md5.c;endline=51;md5=0a448eea0bcbc89e3c7e6608f2d119a0 \
    file://usock.h;endline=18;md5=f0dfdc8de858e66d66d74036611bba14 \
    file://uloop.c;beginline=1;endline=17;md5=f151c0422668fa4c8f91d2caf5267b3e \
"

DEPENDS = "json-c"

SRC_URI = "git://git.openwrt.org/project/libubox.git;protocol=https;nobranch=1 \
           file://0001-make-lua-optional.patch"
SRCREV = "7da66430de3fc235bfc6ebb0b85fb90ea246138d"
S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE = "-DBUILD_LUA=OFF -DBUILD_EXAMPLES=OFF -DCMAKE_SKIP_RPATH=ON -DABIVERSION=1"

FILES:${PN} += "${libdir}/libubox.so.* ${libdir}/libblobmsg_json.so.* ${libdir}/libjson_script.so.* ${bindir}/jshn"
FILES:${PN}-dev += "${libdir}/libubox.so ${libdir}/libblobmsg_json.so ${libdir}/libjson_script.so"
FILES:${PN}-dev += "${includedir}/libubox/*.h"
