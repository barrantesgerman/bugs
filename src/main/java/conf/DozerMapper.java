///*
// * Copyright 2015 Codigo Fantasma.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package conf;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.dozer.DozerBeanMapperSingletonWrapper;
//import org.dozer.Mapper;
//
///**
// *
// * @author Herman
// * @since 24/07/2015
// */
//public class DozerMapper {
//
//    private final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
//
//    public <T> T map(Object object, Class<T> clazz) {
//        return mapper.map(object, clazz);
//    }
//
//    public <T> List<T> map(List lista, Class<T> clazz) {
//        List<T> resultados = new ArrayList<>(lista.size());
//        for (Object objeto : lista) {
//            T resultado = map(objeto, clazz);
//            resultados.add(resultado);
//        }
//        return resultados;
//    }
//
//}
