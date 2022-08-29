plugins {
	id("io.papermc.paperweight.userdev")
	kotlin("jvm")
}

repositories {
	mavenCentral()

	maven("https://repo.papermc.io/repository/maven-public/") // Paper
	maven("https://repo.aikar.co/content/groups/aikar/") // Annotation Command Framework
	maven("https://repo.dmulloy2.net/repository/public") // ProtocolLib
	maven("https://jitpack.io") // Vault
}

dependencies {
	paperDevBundle("1.19.2-R0.1-SNAPSHOT")

	compileOnly("com.comphenix.protocol:ProtocolLib:4.7.0")
	compileOnly("com.github.MilkBowl:VaultAPI:1.7.1")
	compileOnly(project(":IonCore"))

	implementation(project(":Common"))

	implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
}