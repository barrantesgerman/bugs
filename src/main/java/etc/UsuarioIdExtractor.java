package etc;

import ninja.Context;
import ninja.params.ArgumentExtractor;

public class UsuarioIdExtractor implements ArgumentExtractor<Long> {

    @Override
    public Long extract(Context context) {
        // if we got no cookies we break:
        if (context.getSession() != null) {
            Long usuarioId = Long.valueOf(context.getSession().get("usuarioId"));
            return usuarioId;
        }
        return null;
    }

    @Override
    public Class<Long> getExtractedType() {
        return Long.class;
    }

    @Override
    public String getFieldName() {
        return null;
    }

}
