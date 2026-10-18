SUMMARY = "Ambiorix ubus backend"
LICENSE = "SAH & BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f8eaa5ae328592b1346db42ce2936d90"

SRC_URI = "git://gitlab.com/prpl-foundation/components/ambiorix/modules/amxb_backends/amxb_ubus.git;protocol=https;nobranch=1"
SRCREV = "8d2190529eceb61ae4ba095785198e9ad825b433"
S = "${WORKDIR}/git"

inherit pkgconfig config-amx

export CONFIG_SAH_LIB_AMXB_UBUS_ORDER = "05-"

DEPENDS = "libamxc libamxb libamxd ubus"
RDEPENDS:${PN} += "libamxc libamxb libamxd ubus"

do_compile() {
    oe_runmake
}

FILES:${PN}-dev += "${includedir}/amxb_be_ubus/*.h"
FILES:${PN}-dev += "${libdir}/libamxb_ubus.so"
FILES:${PN} += "/usr/bin/mods/amxb/${CONFIG_SAH_LIB_AMXB_UBUS_ORDER}mod-amxb-ubus.so"
FILES:${PN} += "/usr/bin/mods/amxb/mod-amxb-ubus.so"
FILES:${PN} += "${libdir}/libamxb_ubus.so.*"
FILES:${PN} += "${bindir}/forward_ubus_client.sh"
FILES:${PN} += "${bindir}/forward_ubus_server.sh"
