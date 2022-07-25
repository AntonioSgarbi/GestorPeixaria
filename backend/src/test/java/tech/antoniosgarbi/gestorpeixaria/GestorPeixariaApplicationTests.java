package tech.antoniosgarbi.gestorpeixaria;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"tech.antoniosgarbi.gestorpeixaria.service", "tech.antoniosgarbi.gestorpeixaria.controller"})
class GestorPeixariaApplicationTests { }