package com.ecom.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class UploadUtil {

    public static String getFilePath(String urlPath) {
        String reName = "";
        try {
            String imagesPatch = MsgBundleLoader.getConfigProperties("imagesPatch");
            if (StringUtil.isNotNullOrNotEmpty(urlPath)) {
                reName = imagesPatch.concat(urlPath);
            } else {
                reName = imagesPatch.concat("no_img.png");
            }
        } catch (Exception ex) {
            Logger.getLogger(UploadUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reName;
    }

    public static String getFileNameShow(String urlPath) {
        String reName = "";
        try {
            if (urlPath != null && urlPath.length() != 0) {
                String[] fnames = urlPath.split("/");
                if (fnames.length > 0) {
                    reName = fnames[fnames.length - 1];
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UploadUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reName;
    }

    public static String getFileName(UploadedFile files, String fileName) {
        String reName = "";
        try {
            String deName = files.getFileName();
            String extension = deName.substring(deName.length() - 3, deName.length());
            if (fileName != null && fileName.length() != 0) {
                reName = fileName.concat(".").concat(extension.toLowerCase());
            } else {
                reName = deName;
            }
        } catch (Exception ex) {
            Logger.getLogger(UploadUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reName;
    }

    public static StreamedContent getFile(String urlPath, String fileName) {
        StreamedContent file = null;
        try {
            if (urlPath != null && urlPath.length() > 0) {
                String imagesPatch = MsgBundleLoader.getConfigProperties("imagesPatch");
                URL url = new URL(imagesPatch.concat(urlPath));
                URLConnection uc = url.openConnection();
                file = new DefaultStreamedContent(uc.getInputStream(), uc.getContentType(), fileName);
            }
        } catch (Exception ex) {
            Logger.getLogger(UploadUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }

    public static String uploadFile(UploadedFile files, String fileName) {
        String realPath = null;
        try {
            String imagesDir = MsgBundleLoader.getConfigProperties("imagesDir");
            String sFileName = getFileName(files, fileName);
            realPath = "/".concat(sFileName);
            InputStream in = files.getInputstream();
            OutputStream out = new FileOutputStream(new File(imagesDir.concat("/").concat(realPath)));
            int read;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException ioex) {
            Logger.getLogger(UploadUtil.class.getName()).log(Level.SEVERE, null, ioex);
        } catch (Exception ex) {
            Logger.getLogger(UploadUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return realPath;
    }
}
