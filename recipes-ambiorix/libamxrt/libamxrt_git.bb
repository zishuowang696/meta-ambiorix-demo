SUMMARY = "Ambiorix runtime library"
LICENSE = "SAH & BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f8eaa5ae328592b1346db42ce2936d90"

SRC_URI = "git://gitlab.com/prpl-foundation/components/ambiorix/libraries/libamxrt.git;protocol=https;nobranch=1"
SRCREV = "965ab4ec569247527e8a21f8c4de49d7aa2d5eb5"
S = "${WORKDIR}/git"

inherit pkgconfig config-amx

DEPENDS = "libamxc libamxj libamxp libamxd libamxo libamxb libevent libcap-ng"
RDEPENDS:${PN} += "libamxc libamxj libamxp libamxd libamxo libamxb libevent libcap-ng"

do_compile() {
    oe_runmake
}

FILES:${PN} += "${libdir}/libamxrt.so.*"
FILES:${PN}-dev += "${libdir}/libamxrt.so ${includedir}/amxrt/*.h"
FILES:${PN}-staticdev += "${libdir}/libamxrt.a"
