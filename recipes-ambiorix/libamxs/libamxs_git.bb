SUMMARY = "Ambiorix data model synchronization C API"
LICENSE = "SAH & BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bb005226550a047f93eb1924998c2c2f"

SRC_URI = "git://gitlab.com/prpl-foundation/components/ambiorix/libraries/libamxs.git;protocol=https;nobranch=1"
SRCREV = "e3bf6df204ec5beb910059387b258f43d6a9db51"
S = "${WORKDIR}/git"

inherit pkgconfig config-amx

DEPENDS = "libamxc libamxp libamxb libamxd"
RDEPENDS:${PN} += "libamxc libamxp libamxb libamxd"

do_compile() {
    oe_runmake
}

FILES:${PN} += "${libdir}/libamxs.so.*"
FILES:${PN}-dev += "${libdir}/libamxs.so ${includedir}/amxs/*.h"
FILES:${PN}-staticdev += "${libdir}/libamxs.a"
