package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.CodeVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	
	public String boardList(Locale locale, Model model,PageVo pageVo) throws Exception{
		//HashMap<String, String> result = new HashMap<String, String>();
		//CommonUtil commonUtil = new CommonUtil();												
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		List<CodeVo> codeList = new ArrayList<CodeVo>();
		
		
		int page = 1;
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);
		}
		if(pageVo.getCheckRow() == null) {
			//String callMsg ="<script>alert('조회할 항목을 선택해주세요');location.href='/board/boardList.do';</script>";
			//System.out.println(callMsg);
		}
		else {
			String[] checkList = pageVo.getCheckRow().split(",");
			pageVo.setCheckList(checkList);
			
			
		}
		
		boardList = boardService.SelectBoardList(pageVo);
		codeList = boardService.selectCodeList();
		totalCnt = boardService.selectBoardCnt();
		
		model.addAttribute("codeList", codeList);
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		
		/*
		System.out.println("result : "+pageVo.getCheckRow());
		//if(pageVo.getCheckRow() == null) {
			//String callMsg ="<script>alert('조회할 항목을 선택해주세요');location.href='/board/boardList.do';</script>";
			//System.out.println(callMsg);
		//}else {
			String check = pageVo.getCheckRow().toString();
			String[] checkList = check.split(",");
			System.out.println("list : "+ check);

			for(int i=0; i<checkList.length; i++) {
			System.out.println("checklist : "+checkList[i]);
			//}
			
		}
		*/
		
		
		
		
		//System.out.println("param : "+ pageVo.getCheckRow());
		/*
		String checkList[] = null;
		
		String check = result.get("checkParam").toString();
		checkList = check.split(",");
		
		int results = checkList.length;
		int num = 1;
		
		for(int i=0; i<results; i++) {
			HashMap<String, Object> resend = new HashMap<String, Object>();

			resend.put("check", checkList[i]);
			
			results[i] = boardService.selectCodeList(resend);			
		}*/
		//System.out.println("checkList : "+ checkList);

		return "board/boardList";
	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception{
		
		List<CodeVo> codeList = new ArrayList<CodeVo>();
		
		codeList = boardService.selectCodeList();
		
		model.addAttribute("codeList", codeList);

		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,BoardVo boardVo) throws Exception{
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();

		int total = 0 ;
		int listCnt = boardVo.getBoardVoList().size();
		int resultCnt = 0;
		//System.out.printf("listCnt : ", boardVo.getBoardVoList().size());
		
		System.out.println("tttttttttttt" + boardVo.getBoardType());
		for(int i=0; i<listCnt; i++) {
			if(boardVo.getBoardVoList().get(i).getBoardTitle() != null && boardVo.getBoardVoList().get(i).getBoardComment() != null ) {
				boardVo.getBoardVoList().get(i).setBoardType( boardVo.getBoardType());
				resultCnt=boardService.boardInsert(boardVo.getBoardVoList().get(i));
			}
		
			
			
			total += resultCnt;
		}
		result.put("success", (total==listCnt)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
		
		//String titleList[] = boardVo.getBoardTitle().split(",");
		//String commentList[] = boardVo.getBoardComment().split(",");

	}
	
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardUpdate.do", method = RequestMethod.GET)
	public String boardUpdate(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
	
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		int page = 1;
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("pageNo", page);
		model.addAttribute("board", boardVo);
		
		return "board/boardUpdate";
	}
	
	@RequestMapping(value = "/board/boardUpdateAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardUpdateAction(Locale locale,BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardUpdate(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}

	
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardDelete.do", method = RequestMethod.GET)
	@ResponseBody
	public String boardDelete(Locale locale, BoardVo boardVo
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) 
			throws Exception{
		
			HashMap<String, String> result = new HashMap<String, String>();
			CommonUtil commonUtil = new CommonUtil();
		
			//BoardVo boardVo = new BoardVo();
		
			//boardVo = boardService.selectBoard(boardType,boardNum);
			//boardService.boardDelete(boardVo);
			
			//위에걸 밑으로 바꿈
			
			int resultCnt = boardService.boardDelete(boardVo);
			
			result.put("success", (resultCnt > 0)?"It's successfully deleted.":"It's already deleted.");
			
			String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
			
			System.out.println("callbackMsg::"+callbackMsg);
			
			
			return callbackMsg;
			
			//return "redirect:/board/boardList.do";
			
			}	
}

	




