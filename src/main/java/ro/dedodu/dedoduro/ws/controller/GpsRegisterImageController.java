package ro.dedodu.dedoduro.ws.controller;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegister;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegisterImage;
import ro.dedodu.dedoduro.ws.model.repository.GpsRegisterImageRepository;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Controlador que provê o acesso às Imagens dos Eventos
 * registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@RequestMapping("images")
@RestController
public class GpsRegisterImageController {

    private static final String IMG_REPO = "/var/www/html/dedoduro/img/";

    private final GpsRegisterImageRepository repository;

    @Autowired
    public GpsRegisterImageController(GpsRegisterImageRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<GpsRegisterImage> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @RequestMapping(value = "byRegister/{registerId}", method = RequestMethod.GET)
    public Page<GpsRegisterImage> findAllByGpsRegisterId(@PathVariable("registerId") Long registerId, Pageable pageable) {
        GpsRegister register = new GpsRegister();
        register.setId(registerId);

        return repository.findAllByGpsRegister(register, pageable);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public Long save(@RequestBody GpsRegisterImage image) {
        repository.save(image);

        return image.getId();
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public void upload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws IOException {
        Preconditions.checkArgument(!file.isEmpty(), "Arquivo vazio.");

        byte[] bytes = file.getBytes();

        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(Paths.get(IMG_REPO,
                name).toFile()))) {
            stream.write(bytes);
        }
    }
}