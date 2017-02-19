package com.predragvucetic.macro;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.spaces.SpaceManager;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Scanned
public class CalculatorMacro implements Macro {

    private static final String MACRO_BODY_TEMPLATE = "templates/calculator-macro.vm";
    private final SpaceManager spaceManager;

    @Inject
    public CalculatorMacro(@ComponentImport SpaceManager spaceManager)
    {
        this.spaceManager = spaceManager;
    }

    public String execute(Map<String, String> map, String s, ConversionContext conversionContext) throws MacroExecutionException {
        Map<String, Object> context = MacroUtils.defaultVelocityContext();
        if (map.containsKey("Title"))
        {
            context.put("title", map.get("Title"));
        }
        else
        {
            context.put("title", "MAX Calculator");
        }

        context.put("defaultOperation", map.get("Operation"));

        List<String> operations = new ArrayList<String>();
        operations.add("addition");
        operations.add("subtraction");
        operations.add("multiplication");
        operations.add("division");
        context.put("operations", operations);

        return VelocityUtils.getRenderedTemplate(MACRO_BODY_TEMPLATE, context);
    }

    public BodyType getBodyType() {
        return BodyType.NONE;
    }

    public OutputType getOutputType() {
        return OutputType.BLOCK;
    }
}
