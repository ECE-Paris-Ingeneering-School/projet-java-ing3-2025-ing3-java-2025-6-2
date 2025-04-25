package Util;

import Exception.ApplicationException;
import Exception.ErrorType;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    private static ImageManager instance;
    private final Map<String, ImageIcon> imageCache;
    private static final String DEFAULT_IMAGE_PATH = "Main/src/View/image/default_product.png";
    
    private ImageManager() {
        imageCache = new HashMap<>();
    }
    
    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }
    
    public ImageIcon getImage(String category, String productName) {
        String key = category + "_" + productName;
        
        // Vérifier si l'image est dans le cache
        if (imageCache.containsKey(key)) {
            return imageCache.get(key);
        }
        
        // Construire le chemin de l'image
        String imagePath = buildImagePath(category, productName);
        
        try {
            ImageIcon image = loadAndResizeImage(imagePath, 230, 180);
            imageCache.put(key, image);
            return image;
        } catch (Exception e) {
            return getDefaultImage();
        }
    }
    
    private String buildImagePath(String category, String productName) {
        String normalizedCategory = normalizeString(category);
        return String.format("Main/src/View/image/%s/%s", normalizedCategory, productName);
    }
    
    private String normalizeString(String input) {
        return input.toLowerCase()
                   .replace("é", "e")
                   .replace("è", "e")
                   .replace("à", "a")
                   .replace("ù", "u")
                   .replace("ç", "c")
                   .replace("ê", "e");
    }
    
    private ImageIcon loadAndResizeImage(String path, int targetWidth, int targetHeight) {
        File imageFile = new File(path);
        if (!imageFile.exists()) {
            throw new ApplicationException(ErrorType.IMAGE_ERROR, "Image non trouvée: " + path);
        }
        
        ImageIcon originalIcon = new ImageIcon(path);
        Image originalImage = originalIcon.getImage();
        
        Image resizedImage = originalImage.getScaledInstance(
            targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    
    public ImageIcon getDefaultImage() {
        try {
            return loadAndResizeImage(DEFAULT_IMAGE_PATH, 230, 180);
        } catch (Exception e) {
            throw new ApplicationException(
                ErrorType.IMAGE_ERROR, 
                "Impossible de charger l'image par défaut", 
                e
            );
        }
    }
    
    public void clearCache() {
        imageCache.clear();
    }
} 