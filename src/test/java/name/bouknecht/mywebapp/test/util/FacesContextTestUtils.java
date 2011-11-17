package name.bouknecht.mywebapp.test.util;

import javax.faces.context.FacesContext;

public abstract class FacesContextTestUtils extends FacesContext {
    public static void setCurrentInstance(FacesContext facesContext) {
        FacesContext.setCurrentInstance(facesContext);
    }
}
