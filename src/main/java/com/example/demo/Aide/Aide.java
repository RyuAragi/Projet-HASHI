package com.example.demo.Aide;

import com.example.demo.Technique.*;

public interface Aide {

    abstract public Technique detecte();

    abstract public Technique getTechnique();
    abstract public int getPrecision();
}
