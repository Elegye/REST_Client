package com.eseo.client.service;

import com.eseo.client.dto.VilleDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VilleService {

    /**
     *
     * @return
     */
    public static List<VilleDto> getVilles(){
        List<VilleDto> villes = new ArrayList<VilleDto>();

        try {
            String rawResponse = RequestService.get(URLBuilder.GET_VILLES);
            // On retourne un objet VilleDto.
            Gson gson = new Gson();
            villes =  gson.fromJson(rawResponse, new TypeToken<List<VilleDto>>(){}.getType());

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return villes;
    }

    /**
     *
     * @param inseeCode
     * @return
     */
    public static VilleDto getVille(String inseeCode)
    {
        VilleDto ville = null;
        try {
            String rawResponse = RequestService.get(URLBuilder.GET_VILLE + "inseeCode=" + inseeCode);
            System.out.println(rawResponse);
            // On retourne un objet VilleDto.
            Gson gson = new Gson();
            ville =  gson.fromJson(rawResponse, VilleDto.class);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return ville;
    }

    /**
     *
     * @param villeDto
     * @return
     * @throws IOException
     */
    public static int create(VilleDto villeDto) throws IOException {
        try{
            return RequestService.create(villeDto);
        }
        catch (Exception e){
            return -1;
        }
    }

    public static int update(VilleDto villeDto) throws IOException{
        try{
            return RequestService.update(villeDto);
        }
        catch (Exception e){
            return -1;
        }
    }

    public static VilleDto requestToDto(HttpServletRequest request){
        VilleDto dto = new VilleDto();

        dto.setInseeCode(request.getParameter("inseeCode"));
        dto.setName(request.getParameter("cityName"));
        dto.setLabel(request.getParameter("label"));
        dto.setLat(request.getParameter("longitude"));
        dto.setLon(request.getParameter("latitude"));
        dto.setPostalCode(request.getParameter("postalCode"));
        dto.setLine5(request.getParameter("line5"));

        return dto;
    }

    /**
     * Get distance in meters.
     * @param from
     * @param to
     * @return
     * @throws RuntimeException
     */
    public static int getDistance(VilleDto from, VilleDto to) throws RuntimeException{

        double lon1 = Double.parseDouble(from.getLon());
        double lon2 = Double.parseDouble(to.getLon());
        double lat1 = Double.parseDouble(from.getLat());
        double lat2 = Double.parseDouble(to.getLat());

        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        double r = 6371*1000;

        return (int) Math.round(c * r);

    }

}
