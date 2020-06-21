package com.dursun.airways.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dursun.airways.domain.dto.AirPortsDTO;
import com.dursun.airways.domain.dto.AirWayCompanyDTO;
import com.dursun.airways.domain.dto.FlightDefinitionDTO;
import com.dursun.airways.domain.dto.FlightDefinitionsWithDetailsDTO;
import com.dursun.airways.domain.dto.FlightRouteDTO;
import com.dursun.airways.domain.dto.TicketSaleDTO;
import com.dursun.airways.domain.service.AirPortsService;
import com.dursun.airways.domain.service.AirWayCompanyService;
import com.dursun.airways.domain.service.FlightDefinitionService;
import com.dursun.airways.domain.service.FlightRouteService;
import com.dursun.airways.domain.service.TicketSaleService;
import com.dursun.airways.web.request.RequestAirPorts;
import com.dursun.airways.web.request.RequestAirWayCompany;
import com.dursun.airways.web.request.RequestCreateFlightDefinition;
import com.dursun.airways.web.request.RequestFlightRoute;
import com.dursun.airways.web.request.RequestTicketSale;
import com.dursun.airways.web.request.RequestTicketSaleCancel;
import com.dursun.airways.web.request.RequestUpdateFlightQuota;
import com.dursun.airways.web.response.ResponseCreateFlightDefinition;
import com.dursun.airways.web.response.ResponseResult;
import com.dursun.airways.web.response.ResponseTicketSale;

@RestController
public class AirWaysServicesController {

	@Autowired
	AirWayCompanyService airWaysCompanyService;

	@Autowired
	AirPortsService airPortsService;

	@Autowired
	FlightRouteService routeService;

	@Autowired
	FlightDefinitionService flightDefService;

	@Autowired
	TicketSaleService ticketService;

	/**
	 * Havayolu Firmalasi arama
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAirWayCompany", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity getAirWayCompany(RequestAirWayCompany req) {
		AirWayCompanyDTO dto = new AirWayCompanyDTO();
		dto.setName(req.getCompanyName());
		AirWayCompanyDTO details = airWaysCompanyService.recordControl(dto.getName());
		if(details !=null) {
			return new ResponseEntity(details, HttpStatus.OK);
		}else {
			return new ResponseEntity(false, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Havayolu Firmasi Yaratir
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/createAirWayCompany", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseResult> createAirWayCompany(RequestAirWayCompany req) {
		AirWayCompanyDTO dto = new AirWayCompanyDTO();
		dto.setName(req.getCompanyName());
		return new ResponseEntity<ResponseResult>(airWaysCompanyService.createAirWayCompany(dto), HttpStatus.OK);
	}

	/**
	 * HAVAALANI ARAMA
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAirPorts", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity getAirPorts(String shortName) {
		AirPortsDTO details = airPortsService.findAirPortsByShortName(shortName);
		if(details !=null) {
			return new ResponseEntity(details, HttpStatus.OK);
		}else {
			return new ResponseEntity(false, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Havalimani yaratir
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/createAirPorts", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseResult> createAirPorts(RequestAirPorts req) {
		AirPortsDTO dto = new AirPortsDTO();
		dto.setCity(req.getCity());
		dto.setName(req.getName());
		dto.setShortName(req.getShortName());
		return new ResponseEntity<ResponseResult>(airPortsService.createAirPorts(dto), HttpStatus.OK);
	}

	/**
	 * Tanimli rota arama
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getRoute", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity getRoute(RequestFlightRoute req) {
		FlightRouteDTO details = routeService.recordControl(req.getAirPortFrom(), req.getAirPortTo());
		if(details !=null) {
			return new ResponseEntity(true, HttpStatus.OK);
		}else {
			return new ResponseEntity(false, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Rota yaratir
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/createRoute", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseResult> createFlightRoute(RequestFlightRoute req) {
		FlightRouteDTO dto = new FlightRouteDTO();
		dto.setAirPortFrom(req.getAirPortFrom());
		dto.setAirPortTo(req.getAirPortTo());
		return new ResponseEntity<ResponseResult>(routeService.createFlightRoute(dto), HttpStatus.OK);
	}

	/**
	 * Tum Tanimli Ucuslari listeler
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllFlightDefinition", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getAllFlightDefinition() {
		List<FlightDefinitionDTO> details = flightDefService.getAllFlightDefinition();
		return new ResponseEntity(details, HttpStatus.OK);
	}
	
	/**
	 * TTanimli ucus aramasi
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getFlightDefinition", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity getFlightDefinition(Long flightID) {
		FlightDefinitionsWithDetailsDTO details = flightDefService.findFlightByID(flightID);
		if(details !=null) {
			return new ResponseEntity(details, HttpStatus.OK);
		}else {
			return new ResponseEntity(false, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Ucus Tanimlama yapar
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/createFlightDefinition", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseCreateFlightDefinition> createFlightDefinition(RequestCreateFlightDefinition req) {
		ResponseCreateFlightDefinition response = new ResponseCreateFlightDefinition();
		FlightDefinitionDTO dto = flightDefService.createFlightDefinition(req);
		response.setFlightID(dto.getFlightID());
		response.setErrorDescription(dto.getErrorDescription());
		response.setErrorID(dto.getErrorID());
		response.setStatus(dto.getStatus());
		return new ResponseEntity<ResponseCreateFlightDefinition>(response, HttpStatus.OK);
	}

	/**
	 * Tanimli ucuslarda kota guncellemesi yapar
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateFlightDefinition", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseResult> updateFlightDefinition(RequestUpdateFlightQuota req) {
		return new ResponseEntity<ResponseResult>(flightDefService.updateFlightQuota(req), HttpStatus.OK);
	}

	/**
	 * Bilet Satis
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/ticketSale", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseTicketSale> ticketSale(RequestTicketSale req) {
		ResponseTicketSale response = new ResponseTicketSale();
		TicketSaleDTO dto = ticketService.createTicketSale(req);
		response.setErrorDescription(dto.getErrorDescription());
		response.setErrorID(dto.getErrorID());
		response.setStatus(dto.getStatus());
		response.setTicketNumber(dto.getId());
		return new ResponseEntity<ResponseTicketSale>(response, HttpStatus.OK);
	}

	/**
	 * Bilet Iptali
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/ticketSaleCancel", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseTicketSale> ticketSaleCancel(RequestTicketSaleCancel req) {
		ResponseTicketSale response = new ResponseTicketSale();
		TicketSaleDTO dto = ticketService.createTicketSaleCancel(req);
		response.setErrorDescription(dto.getErrorDescription());
		response.setErrorID(dto.getErrorID());
		response.setStatus(dto.getStatus());
		response.setTicketNumber(dto.getId());
		return new ResponseEntity<ResponseTicketSale>(response, HttpStatus.OK);
	}
	
	/**
	 * Bilet sorgulama
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/ticketSaleSearch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseTicketSale> ticketSaleSearch(Long ticketId) {
		TicketSaleDTO details = ticketService.recordControl(ticketId);
		if(details !=null) {
			return new ResponseEntity(details, HttpStatus.OK);
		}else {
			return new ResponseEntity(false, HttpStatus.NOT_FOUND);
		}
	}
}
