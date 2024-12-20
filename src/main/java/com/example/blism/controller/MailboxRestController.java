package com.example.blism.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blism.apiPayload.ApiResponse;
import com.example.blism.dto.request.MailboxVisibilityRequestDTO;
import com.example.blism.dto.response.MailboxResponseDTO;
import com.example.blism.dto.response.PastMailboxListResponseDTO;
import com.example.blism.service.MailboxService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mailboxes")
public class MailboxRestController {
	private final MailboxService mailboxService;

	@GetMapping
	@Operation(summary = "멤버의 우체통 조회", description = "특정 멤버의 우체통을 반환합니다.")
	public ApiResponse<MailboxResponseDTO> getMailbox (
		@RequestParam Long memberId
	){
		MailboxResponseDTO mailboxResponseDTO = mailboxService.getMailbox(memberId);
		return ApiResponse.onSuccess(mailboxResponseDTO);
	}

	@GetMapping("/past")
	@Operation(summary = "나의 과거 우체통 리스트 조회", description = "나의 과거 우체통 리스트를 반환합니다.")
	public ApiResponse<PastMailboxListResponseDTO> getPastMailboxList (
		@RequestParam Long memberId
	){
		PastMailboxListResponseDTO pastMailboxes = mailboxService.getPastMailboxList(memberId);
		return ApiResponse.onSuccess(pastMailboxes);
	}

	@GetMapping("/past/{memberId}")
	@Operation(summary = "특정 년도 우체통 조회", description = "특정 연도 우체통을 조회합니다.")
	public ApiResponse<MailboxResponseDTO> getPastMailbox (
		@RequestParam String year,
		@PathVariable Long memberId
	){
		MailboxResponseDTO pastMailboxResponseDTO = mailboxService.getPastMailbox(year, memberId);
		return ApiResponse.onSuccess(pastMailboxResponseDTO);
	}

	@PatchMapping("/visibility")
	@Operation(summary = "우체통 공개 여부 수정", description = "우체통 공개 여부를 수정합니다.")
	public ApiResponse<String> changeVisibility(
		@RequestBody MailboxVisibilityRequestDTO requestDTO
	){
		return ApiResponse.onSuccess(mailboxService.updateVisibility(requestDTO));
	}

}
