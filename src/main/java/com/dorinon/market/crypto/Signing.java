package com.dorinon.market.crypto;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

public final class Signing {
    private static final int KEY_SIZE = 2048;

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public Signing(@NotNull Plugin plugin) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        File file = new File(plugin.getDataFolder(), "keypair.yml");
        if (!file.exists()) file.createNewFile();
        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);

        if (fileConfig.contains("private") && fileConfig.contains("public")) {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(
                    Base64.getDecoder().decode(Objects.requireNonNull(fileConfig.getString("private")))
            ));

            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(
                    Base64.getDecoder().decode(Objects.requireNonNull(fileConfig.getString("public")))
            ));
        } else {
            fileConfig = new YamlConfiguration();

            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(KEY_SIZE);
            KeyPair pair = generator.generateKeyPair();

            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();

            fileConfig.set("private", Base64.getEncoder().withoutPadding().encodeToString(privateKey.getEncoded()));
            fileConfig.set("public", Base64.getEncoder().withoutPadding().encodeToString(publicKey.getEncoded()));

            fileConfig.save(file);
        }
    }
}
