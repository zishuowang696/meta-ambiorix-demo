SUMMARY = "Ambiorix bus agnostic C API"
LICENSE = "SAH & BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bb005226550a047f93eb1924998c2c2f"

SRC_URI = "git://gitlab.com/prpl-foundation/components/ambiorix/libraries/libamxb.git;protocol=https;nobranch=1"
SRCREV = "30a516dbcb2e3cecfad6eb91fc30f50875f8cf6b"
S = "${WORKDIR}/git"

inherit pkgconfig config-amx

DEPENDS = "libamxc libamxp libamxd uriparser"
RDEPENDS:${PN} += "libamxc libamxp libamxd uriparser"

do_compile() {
    oe_runmake
}

FILES:${PN} += "${libdir}/libamxb.so.*"
FILES:${PN}-dev += "${libdir}/libamxb.so ${includedir}/amxb/*.h"
FILES:${PN}-staticdev += "${libdir}/libamxb.a"
