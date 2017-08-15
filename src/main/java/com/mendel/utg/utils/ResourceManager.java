package com.mendel.utg.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Alberto Alegria
 */
public class ResourceManager {
    public static final ResourceBundle CONFIG = ResourceBundle.getBundle("configuration");
    public static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages", Locale.US);
}
