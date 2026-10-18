SUMMARY = "Ambiorix data model C API"
LICENSE = "SAH & BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bb005226550a047f93eb1924998c2c2f"

SRC_URI = "git://gitlab.com/prpl-foundation/components/ambiorix/libraries/libamxd.git;protocol=https;nobranch=1"
SRCREV = "a5ef531b294743a510b205d4031fd2ee02c8d236"
S = "${WORKDIR}/git"

inherit pkgconfig config-amx

DEPENDS = "libamxc libamxp"
RDEPENDS:${PN} += "libamxc libamxp"

do_compile() {
    oe_runmake
}

FILES:${PN} += "${libdir}/libamxd.so.*"
FILES:${PN}-dev += "${libdir}/libamxd.so ${includedir}/amxd/*.h"
FILES:${PN}-staticdev += "${libdir}/libamxd.a"
