import org.springframework.stereotype.Service

@Service("manService")
class ManServiceImpl implements ManService {

    @Override
    Man getInfoByName(String name) {
        return new Man(30, name, "zzww")
    }
}