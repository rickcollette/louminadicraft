 📜 INSTALL.md — Lou Minadi Craft

## 📦 What is this?
**LouMinadiCraft** is a custom Minecraft plugin featuring surreal biomes, lore quests, strange world events, and hidden bosses.  
Built for Spigot/Paper 1.20+ servers.

---

## 🛠 How to Build

> **Requirements**  
> • Java 17+  
> • Maven 3.6+  
> • Make (optional, but recommended)

---

### 🔹 Quick Build (Recommended)

1. Open a terminal in the project root directory.
2. Run:
   ```bash
   make
   ```
3. The final plugin will be created at:
   ```
   output/LouMinadiCraft.jar
   ```

---

### 🔹 Manual Build (If you don't have `make`)

1. Open terminal in the project directory.
2. Run:
   ```bash
   mvn clean package
   ```
3. Copy the built jar manually:
   ```bash
   mkdir -p output
   cp target/LouMinadiCraft-1.0.0.jar output/LouMinadiCraft.jar
   ```

---

## 🚀 How to Install on your Server

1. Make sure your server is **Spigot** or **Paper** (1.20.1+ recommended).
2. Copy the plugin jar:
   ```
   output/LouMinadiCraft.jar
   ```
   into your server’s:
   ```
   plugins/
   ```
   folder.
3. Start or restart your server.
4. Watch your console for:
   ```
   [LouMinadiCraft] LouMinadiCraft is powering up!
   ```

---

## 🌍 World Setup

| World Name           | World Type        | Description |
| :------------------- | :---------------- | :------------------------------------------------ |
| `world_billyd`        | Custom Jungle      | Ancient serpent jungles with mist and temples |
| `world_moneyshot`     | Decayed Badlands   | Wasteland megacity ruins and acid storms |
| `world_conditions`    | Dark Forest Craters | Industrial forest ruins and earthquakes |
| `world_existential`   | Existential Reckoning | Floating void islands, rift anomalies, glitch storms |

**Tip:** You can pre-create these worlds using a multiworld plugin like **Multiverse-Core**, then assign their generators.

---

## 🧹 Extra Maintenance Commands

| Command             | Purpose                         |
| :------------------ | :------------------------------ |
| `make clean`        | Clean build artifacts (`target/`) |
| `make copy-only`    | Only re-copy the latest built jar |
| `make distclean`    | Full clean: remove `target/` and `output/` |

---

## 📋 Notes

- If you encounter `cannot find symbol` errors during build, make sure all `.java` files are present.
- The plugin dynamically applies different world generators based on the world name.
- Some rare events, like Rift Storms or Strange Entities, only happen under certain conditions.
- LouMinadiCraft is designed to enhance exploration, mystery, and player-driven lore.

---

# 🔥 Have fun bending reality!
