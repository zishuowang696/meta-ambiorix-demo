SUMMARY = "Ambiorix common patterns implementation"
LICENSE = "SAH & BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f8eaa5ae328592b1346db42ce2936d90"

SRC_URI = "git://gitlab.com/prpl-foundation/components/ambiorix/libraries/libamxp.git;protocol=https;nobranch=1"
SRCREV = "9d16f87bdbf87043e449551be074798d669463be"
S = "${WORKDIR}/git"

inherit pkgconfig config-amx

DEPENDS = "libamxc libcap-ng bison-native flex-native"
RDEPENDS:${PN} += "libamxc libcap-ng"

do_compile() {
    oe_runmake
}

FILES:${PN} += "${libdir}/libamxp.so.*"
FILES:${PN}-dev += "${libdir}/libamxp.so ${includedir}/amxp/*.h"
FILES:${PN}-staticdev += "${libdir}/libamxp.a"
