# meta-ambiorix-demo

[![yocto](https://github.com/zishuowang696/meta-ambiorix-demo/actions/workflows/yocto.yml/badge.svg)](https://github.com/zishuowang696/meta-ambiorix-demo/actions/workflows/yocto.yml)

A **minimal, reproducible Yocto layer** that runs [Ambiorix (AMX)](https://gitlab.com/prpl-foundation/components/ambiorix) on **scarthgap** in QEMU.

It builds the full AMX library chain, the `amxrt` runtime, the **ubus** backend, and a tiny `Hello` data model served over ubus. A `hello-client` reads it over ubus.

> The upstream [`meta-amx`](https://gitlab.com/prpl-foundation/yocto/metalayers/meta-amx) layer only declares `honister`. This repo is a small scarthgap demo — not a replacement for upstream, and not affiliated with PRPL Foundation.

## What it proves

- The AMX chain builds with a modern Yocto toolchain: `libamxc → libamxj/libamxp → libamxd → libamxb/libamxs → libamxo → libamxrt → amxrt`.
- `amxrt` loads an ODL data model and exposes it over ubus.
- systemd starts `ubusd` and the AMX service in the right order.
- A client can read the object tree over ubus.

## Quick start

Requirements: `git`, `kas`, network access, ~50 GB free disk, 8+ GB RAM.

```bash
git clone https://github.com/zishuowang696/meta-ambiorix-demo.git
cd meta-ambiorix-demo

kas build kas.yml
kas shell kas.yml -c "runqemu qemuarm64 nographic slirp"
```

Log in as `root` (no password on the serial console).

Inside QEMU:

```bash
systemctl status hello
ubus list
hello-client
```

Expected `hello-client` output (trimmed):

```
{
	"Hello.": {
		"Count": 0,
		"Greeting": "Hello, AMX"
	}
}
```

## Layout

```
meta-ambiorix-demo/
├── kas.yml                       # scarthgap + qemuarm64 + meta-oe
├── conf/layer.conf
├── classes/config-amx.bbclass    # BAF makefile glue (DEST/LIBDIR/...)
├── licenses/SAH                   # SoftAtHome license text
├── recipes-ambiorix/             # libamxc..amxrt, ubus backend, hello, client
└── recipes-core/                 # ubus, libubox, ubusd systemd unit, packagegroup
```

## How it works

1. `kas.yml` pulls `poky` and `meta-openembedded` at `scarthgap`, adds this layer, and installs `packagegroup-ambiorix-demo`.
2. Each AMX recipe calls the upstream Makefile with the paths Yocto expects:
   ```bitbake
   EXTRA_OEMAKE += "DEST=${D} PREFIX=${prefix} LIBDIR=${libdir} BINDIR=${bindir} INCLUDEDIR=${includedir}"
   ```
   `DEST` is the BAF equivalent of `DESTDIR`; `LIBDIR` must be overridden or the Makefile derives `lib/<target-triple>` from `$(CC) -dumpmachine`.
3. `ubusd.service` starts the bus, `hello.service` runs:
   ```
   amxrt -u ubus:/var/run/ubus/ubus.sock \
         -B /usr/bin/mods/amxb/mod-amxb-ubus.so \
         /etc/amx/hello/hello.odl
   ```
4. `hello-client` calls `ubus call Hello _get` to read the object tree.

## Notes

- **Licenses**: the AMX components use `SAH & BSD-2-Clause-Patent`. `SAH` is SoftAtHome's own license text; check the `LICENSE` file in each repository and **confirm licensing before shipping a product**. `ubus` is LGPL-2.1-or-later; `libubox` is BSD.
- **Lua is disabled** in `ubus`/`libubox` via `0001-make-lua-optional.patch`. The demo does not need the Lua bindings, and this avoids pinning Lua 5.3 on scarthgap.
- **SRCREVs are pinned** to the versions used by upstream `meta-amx` v15.17.x. Update them deliberately.
- **QEMU only**: no hardware-specific BSP is included. For a real board, swap `machine` in `kas.yml` and add your BSP layer.

## Troubleshooting

| Symptom | Check |
|---|---|
| `hello` is not running | `journalctl -u hello -u ubusd` |
| `hello-client` prints nothing | `systemctl status ubusd`; socket is `/var/run/ubus/ubus.sock` |
| `ubus list` shows no `Hello` | `systemctl status hello`; check the ODL path in `hello.service` |
| Build fails on `-Werror` | add `CFLAGS:append = " -Wno-error"` to `local.conf` |

## Known issue

With `amxb_ubus` 3.11.0, a standalone `libamxb` client can get `object_not_found` from `amxb_get`/`amxb_call` even though `ubus call Hello _get` works. The demo therefore uses the `ubus` CLI for `hello-client`; the AMX service, ODL and backend registration are unchanged.

## References

- Upstream Yocto layer: <https://gitlab.com/prpl-foundation/yocto/metalayers/meta-amx>
- Ambiorix components: <https://gitlab.com/prpl-foundation/components/ambiorix>
- Background articles (Chinese/English):
  - [Integrating Ambiorix into Yocto](https://embedai.com.cn/en/posts/yocto-integrate-ambiorix)
  - [What AMXD means](https://embedai.com.cn/en/posts/what-amxd-means)
  - [libamxd in code](https://embedai.com.cn/en/posts/amx-libamxd-in-code)
