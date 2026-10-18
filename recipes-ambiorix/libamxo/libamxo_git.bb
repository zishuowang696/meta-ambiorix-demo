SUMMARY = "Ambiorix Object Definition Language library"
LICENSE = "SAH & BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bb005226550a047f93eb1924998c2c2f"

SRC_URI = "git://gitlab.com/prpl-foundation/components/ambiorix/libraries/libamxo.git;protocol=https;nobranch=1"
SRCREV = "4c28a6b092c93305fe4cac8d2e5503d5325d8464"
S = "${WORKDIR}/git"

inherit pkgconfig config-amx

DEPENDS = "libamxc libamxp libamxd libamxs bison-native flex-native"
RDEPENDS:${PN} += "libamxc libamxp libamxd libamxs"

do_compile() {
    oe_runmake
}

FILES:${PN} += "${libdir}/libamxo.so.*"
FILES:${PN}-dev += "${libdir}/libamxo.so ${includedir}/amxo/*.h"
FILES:${PN}-staticdev += "${libdir}/libamxo.a"
