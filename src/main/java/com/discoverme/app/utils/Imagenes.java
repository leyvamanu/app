package com.discoverme.app.utils;

import com.discoverme.app.domain.Foto;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase que nos da soporte para tratar las imagenes que se suben al server
 * @author leyva
 */
public class Imagenes {
        
    private static final String RUTA = "/opt/payara5/glassfish/domains/domain1/applications/DiscoverMe/WEB-INF/resources/img/";
    private static final int IMG_WIDTH = 900;
    private static final int IMG_HEIGHT = 600;
    
    /**
     * Funcion para subir una imagen al server
     * @param imagen
     * @param destacada
     * @param nombreActividad
     * @param numeroFoto
     * @param idFoto
     * @return 
     */
    public static Foto subirFotosServer(MultipartFile imagen,boolean destacada,String nombreActividad,int numeroFoto,Integer idFoto){
        //guardamos la imagen en el server
        String extension = imagen.getOriginalFilename().split("\\.")[1];
        String nombreActividadSinEspacios = nombreActividad.replace(' ', '-');
        String nombre = nombreActividadSinEspacios+"-"+numeroFoto+"."+extension;
        try {
            byte[] bytes = imagen.getBytes();
            File serverFile = new File(RUTA + nombre);
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                stream.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //redimensionamos
	try{
            System.out.println(RUTA + nombre);
            BufferedImage originalImage = ImageIO.read(new File(RUTA + nombre));
            int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizeImageJpg = resizeImage(originalImage, type);
            ImageIO.write(resizeImageJpg, extension, new File(RUTA + nombre));
        }catch(IOException e){
            e.printStackTrace();
        }

        //creamos la Foto y la retornamos
        Foto foto = new Foto();
        if(idFoto != null){
            foto.setId(idFoto);
        }
        foto.setFoto(nombre);
        foto.setLogo(destacada);
        
        return foto;
    }
    
    /**
     * Funcion que redimensiona una imagen
     * @param originalImage
     * @param type
     * @return 
     */
    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    /**
     * Funcion que elimina una imagen
     * @param foto 
     */
    public static void eliminarFotosServer(Foto foto){
        File fichero = new File(RUTA+foto.getFoto());
        fichero.delete();
    }
}
