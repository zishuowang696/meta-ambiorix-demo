SUMMARY = "OpenWrt system message/RPC bus"
HOMEPAGE = "https://git.openwrt.org/project/ubus.git"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://ubusd.c;beginline=1;endline=12;md5=1b6a7aecd35bdd25de35da967668485d"

DEPENDS = "json-c libubox"

SRC_URI = "git://git.openwrt.org/project/ubus.git;protocol=https;nobranch=1 \
           file://0001-make-lua-optional.patch"
SRCREV = "d35df8adda873dc75d876f72b78e84db8cfa72ee"
S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE = "-DBUILD_LUA=OFF -DBUILD_EXAMPLES=OFF -DABIVERSION=1"

PACKAGES =+ "${PN}d"

FILES:${PN} = "${bindir}/ubus ${libdir}/libubus.so.*"
FILES:${PN}-dev = "${libdir}/libubus.so ${includedir}/libubus.h ${includedir}/ubusmsg.h ${includedir}/ubus_common.h"
FILES:${PN}d = "${sbindir}/ubusd"
